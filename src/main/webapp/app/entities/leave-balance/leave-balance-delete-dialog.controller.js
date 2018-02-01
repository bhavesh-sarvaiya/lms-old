(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveBalanceDeleteController',LeaveBalanceDeleteController);

    LeaveBalanceDeleteController.$inject = ['$uibModalInstance', 'entity', 'LeaveBalance'];

    function LeaveBalanceDeleteController($uibModalInstance, entity, LeaveBalance) {
        var vm = this;

        vm.leaveBalance = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LeaveBalance.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
