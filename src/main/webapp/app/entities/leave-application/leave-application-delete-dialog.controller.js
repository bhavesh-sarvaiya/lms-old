(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationDeleteController',LeaveApplicationDeleteController);

    LeaveApplicationDeleteController.$inject = ['$uibModalInstance', 'entity', 'LeaveApplication'];

    function LeaveApplicationDeleteController($uibModalInstance, entity, LeaveApplication) {
        var vm = this;

        vm.leaveApplication = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LeaveApplication.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
