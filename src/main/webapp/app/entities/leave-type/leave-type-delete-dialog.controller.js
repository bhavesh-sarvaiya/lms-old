(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveTypeDeleteController',LeaveTypeDeleteController);

    LeaveTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'LeaveType'];

    function LeaveTypeDeleteController($uibModalInstance, entity, LeaveType) {
        var vm = this;

        vm.leaveType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LeaveType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
