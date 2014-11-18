(function () {
    if (!window.application)
        window.application = {
        };

    /**
     * Method to check the network status
     */
    application.checkConnection = function () {
        var networkState = navigator.network.connection.type;
        var states = {
        };
        states[Connection.UNKNOWN] = 'Unknown connection';
        states[Connection.ETHERNET] = 'Ethernet connection';
        states[Connection.WIFI] = 'WiFi connection';
        states[Connection.CELL_2G] = 'Cell 2G connection';
        states[Connection.CELL_3G] = 'Cell 3G connection';
        states[Connection.CELL_4G] = 'Cell 4G connection';
        states[Connection.NONE] = 'No network connection';
      if (networkState == Connection.NONE || networkState == Connection.UNKNOWN) {               
          adf.mf.api.invokeMethod("com.tamcapp.mobilebook.ses.mobile.model.ConferenceSessions",
                                  "setStatusAndSave", "false", onSuccess, onFail);
      } 
      else { 
          adf.mf.api.invokeMethod("com.tamcapp.mobilebook.ses.mobile.model.ConferenceSessions", 
                                  "setStatusAndSave", "true", onSuccess, onFail);
      }
    }
 
    function onSuccess(param) { }
    function onFail() {}
})();
