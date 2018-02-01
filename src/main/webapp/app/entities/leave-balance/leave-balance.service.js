(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('LeaveBalance', LeaveBalance);

    LeaveBalance.$inject = ['$resource'];

    function LeaveBalance ($resource) {
        var resourceUrl =  'api/leave-balances/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
