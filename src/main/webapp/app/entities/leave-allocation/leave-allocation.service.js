(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('LeaveAllocation', LeaveAllocation);

    LeaveAllocation.$inject = ['$resource', 'DateUtils'];

    function LeaveAllocation ($resource, DateUtils) {
        var resourceUrl =  'api/leave-allocations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.allocationDate = DateUtils.convertLocalDateFromServer(data.allocationDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.allocationDate = DateUtils.convertLocalDateToServer(copy.allocationDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.allocationDate = DateUtils.convertLocalDateToServer(copy.allocationDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
