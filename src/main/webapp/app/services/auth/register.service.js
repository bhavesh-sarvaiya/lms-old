(function () {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
