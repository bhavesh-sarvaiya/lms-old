(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('LeaveApplicationHistory', LeaveApplicationHistory);

    LeaveApplicationHistory.$inject = ['$resource', 'DateUtils'];

    function LeaveApplicationHistory ($resource, DateUtils) {
        var resourceUrl =  'api/leave-application-histories/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.actionDate = DateUtils.convertLocalDateFromServer(data.actionDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.actionDate = DateUtils.convertLocalDateToServer(copy.actionDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.actionDate = DateUtils.convertLocalDateToServer(copy.actionDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
