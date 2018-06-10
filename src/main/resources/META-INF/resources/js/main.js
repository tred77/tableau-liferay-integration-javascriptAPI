var _Tableau = (function () {
    if (_Tableau)
        return;

    createViz = function (containerID, url, options) {
        var _viz;
        var _id = containerID;
        var _url = url;
        var _options = options;
        var _placeholderDiv = document.getElementById(_id);
        var _workbook;
        var _default_options = {
            width: _placeholderDiv.offsetWidth,
            height: _placeholderDiv.offsetHeight,
            hideTabs: false,
            hideToolbar: false,
            onFirstInteractive: function () {
                _workbook = _viz.getWorkbook();
            }
        };

        function _getActiveSheet() {
            return _workbook.getActiveSheet();
        }

        function _selectAllByField(fieldName) {
            _getActiveSheet().applyFilterAsync(
                fieldName,
                "ALL",
                tableau.FilterUpdateType.REPLACE);
        }

        (function _load(tableau) {
            _viz = new tableau.Viz(_placeholderDiv, _url, Object.assign(_default_options, _options));

        }(tableau));

        function _getSheets() {
            return _workbook.getPublishedSheetsInfo();
        }

        function _getNextSheet() {
            var activeSheet = _getActiveSheet();
            var sheets = _getSheets();
            for (var i = 0, len = sheets.length; i < len; i++) {
                if(sheets[i].getName() == activeSheet.getName()){
                    if(i+1 < sheets.length) {
                        return sheets[i + 1];
                    }else{
                        return sheets[0];
                    }
                }
            }
        }

        function _switchToTab(tabName) {
            _workbook.activateSheetAsync(tabName);
        }

        function _nextSheet(){
            _switchToTab(_getNextSheet().getName());
        }

        function _getUnderlyingData(){
            var sheet = _getActiveSheet();
            if(sheet.getSheetType() == "dashboard"){
                alert("This tab is a dashboard and may have more than one sheet!");
                return;
            }
            options = {
                maxRows: 0, // Max rows to return. Use 0 to return all rows
                ignoreAliases: false,
                ignoreSelection: true,
                includeAllColumns: false
            };

            sheet.getUnderlyingDataAsync(options).then(function(t){
                var table = t;
                alert(JSON.stringify(table.getData()));
            });
        }

        return {
            selectAllByField: _selectAllByField,
            goNextTab: _nextSheet,
            getActiveTabData: _getUnderlyingData
        }
    }

    return {
        create: createViz
    }

}());