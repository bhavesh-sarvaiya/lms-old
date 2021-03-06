(function() {
    'use strict';
    angular
        .module('leaveManagementSystemApp')
        .factory('DepartmentService', DepartmentService);

    DepartmentService.$inject = ['$resource'];

    function DepartmentService ($resource) {
        var resourceUrl =  'api/departments/:id';

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
