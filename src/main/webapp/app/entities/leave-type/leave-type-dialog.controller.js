(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveTypeDialogController', LeaveTypeDialogController);

    LeaveTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LeaveType'];

    function LeaveTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LeaveType) {
        var vm = this;

        vm.leaveType = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.leaveType.id !== null) {
                LeaveType.update(vm.leaveType, onSaveSuccess, onSaveError);
            } else {
                LeaveType.save(vm.leaveType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('leaveManagementSystemApp:leaveTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
