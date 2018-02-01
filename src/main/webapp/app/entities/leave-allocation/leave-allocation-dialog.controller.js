(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveAllocationDialogController', LeaveAllocationDialogController);

    LeaveAllocationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LeaveAllocation', 'LeaveType'];

    function LeaveAllocationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LeaveAllocation, LeaveType) {
        var vm = this;

        vm.leaveAllocation = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.leavetypes = LeaveType.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.leaveAllocation.id !== null) {
                LeaveAllocation.update(vm.leaveAllocation, onSaveSuccess, onSaveError);
            } else {
                LeaveAllocation.save(vm.leaveAllocation, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('leaveManagementSystemApp:leaveAllocationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.allocationDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
