(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationHistoryDeleteController',LeaveApplicationHistoryDeleteController);

    LeaveApplicationHistoryDeleteController.$inject = ['$uibModalInstance', 'entity', 'LeaveApplicationHistory'];

    function LeaveApplicationHistoryDeleteController($uibModalInstance, entity, LeaveApplicationHistory) {
        var vm = this;

        vm.leaveApplicationHistory = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LeaveApplicationHistory.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
