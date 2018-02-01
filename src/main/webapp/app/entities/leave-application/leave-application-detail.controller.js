(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationDetailController', LeaveApplicationDetailController);

    LeaveApplicationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LeaveApplication', 'LeaveType'];

    function LeaveApplicationDetailController($scope, $rootScope, $stateParams, previousState, entity, LeaveApplication, LeaveType) {
        var vm = this;

        vm.leaveApplication = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('leaveManagementSystemApp:leaveApplicationUpdate', function(event, result) {
            vm.leaveApplication = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
