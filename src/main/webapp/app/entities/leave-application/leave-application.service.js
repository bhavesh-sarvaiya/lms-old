(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('LeaveApplication', LeaveApplication);

    LeaveApplication.$inject = ['$resource', 'DateUtils'];

    function LeaveApplication ($resource, DateUtils) {
        var resourceUrl =  'api/leave-applications/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateFrom = DateUtils.convertLocalDateFromServer(data.dateFrom);
                        data.endDate = DateUtils.convertLocalDateFromServer(data.endDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateFrom = DateUtils.convertLocalDateToServer(copy.dateFrom);
                    copy.endDate = DateUtils.convertLocalDateToServer(copy.endDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateFrom = DateUtils.convertLocalDateToServer(copy.dateFrom);
                    copy.endDate = DateUtils.convertLocalDateToServer(copy.endDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
