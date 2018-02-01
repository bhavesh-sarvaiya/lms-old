(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveBalanceDetailController', LeaveBalanceDetailController);

    LeaveBalanceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LeaveBalance'];

    function LeaveBalanceDetailController($scope, $rootScope, $stateParams, previousState, entity, LeaveBalance) {
        var vm = this;

        vm.leaveBalance = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('leaveManagementSystemApp:leaveBalanceUpdate', function(event, result) {
            vm.leaveBalance = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
