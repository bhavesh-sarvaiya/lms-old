(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('Employee', Employee);

    Employee.$inject = ['$resource', 'DateUtils'];

    function Employee ($resource, DateUtils) {
        var resourceUrl =  'api/employees/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dob = DateUtils.convertLocalDateFromServer(data.dob);
                        data.joinDate = DateUtils.convertLocalDateFromServer(data.joinDate);
                        data.retiredDate = DateUtils.convertLocalDateFromServer(data.retiredDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dob = DateUtils.convertLocalDateToServer(copy.dob);
                    copy.joinDate = DateUtils.convertLocalDateToServer(copy.joinDate);
                    copy.retiredDate = DateUtils.convertLocalDateToServer(copy.retiredDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dob = DateUtils.convertLocalDateToServer(copy.dob);
                    copy.joinDate = DateUtils.convertLocalDateToServer(copy.joinDate);
                    copy.retiredDate = DateUtils.convertLocalDateToServer(copy.retiredDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
