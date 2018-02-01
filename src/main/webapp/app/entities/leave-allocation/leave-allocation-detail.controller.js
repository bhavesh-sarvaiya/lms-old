(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveAllocationDetailController', LeaveAllocationDetailController);

    LeaveAllocationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LeaveAllocation', 'LeaveType'];

    function LeaveAllocationDetailController($scope, $rootScope, $stateParams, previousState, entity, LeaveAllocation, LeaveType) {
        var vm = this;

        vm.leaveAllocation = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('leaveManagementSystemApp:leaveAllocationUpdate', function(event, result) {
            vm.leaveAllocation = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
