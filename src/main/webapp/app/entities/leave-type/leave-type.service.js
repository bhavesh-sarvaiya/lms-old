(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('LeaveType', LeaveType);

    LeaveType.$inject = ['$resource'];

    function LeaveType ($resource) {
        var resourceUrl =  'api/leave-types/:id';

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
