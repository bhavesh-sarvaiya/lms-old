(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveAllocationDeleteController',LeaveAllocationDeleteController);

    LeaveAllocationDeleteController.$inject = ['$uibModalInstance', 'entity', 'LeaveAllocation'];

    function LeaveAllocationDeleteController($uibModalInstance, entity, LeaveAllocation) {
        var vm = this;

        vm.leaveAllocation = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LeaveAllocation.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
