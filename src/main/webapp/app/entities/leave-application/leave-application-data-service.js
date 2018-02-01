(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('LeaveApplicationDataService', LeaveApplicationDataService);

    LeaveApplicationDataService.$inject = ['$http'];

    function LeaveApplicationDataService($http) {

    	 function getNoOfDays(leaveApplication) {
             return $http.post('/api/leave-application/calculateNoOfDays',leaveApplication)
             .then(function(response) 
            {
                 return response.data;
             });
         }
    	 
          return{
        	  getNoOfDays:getNoOfDays
        	 
          }
    }
})();