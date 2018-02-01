(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationHistoryDialogController', LeaveApplicationHistoryDialogController);

    LeaveApplicationHistoryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LeaveApplicationHistory'];

    function LeaveApplicationHistoryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LeaveApplicationHistory) {
        var vm = this;

        vm.leaveApplicationHistory = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.leaveApplicationHistory.id !== null) {
                LeaveApplicationHistory.update(vm.leaveApplicationHistory, onSaveSuccess, onSaveError);
            } else {
                LeaveApplicationHistory.save(vm.leaveApplicationHistory, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('leaveManagementSystemApp:leaveApplicationHistoryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.actionDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
