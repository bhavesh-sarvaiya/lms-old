(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveBalanceDialogController', LeaveBalanceDialogController);

    LeaveBalanceDialogController.$inject = ['$timeout', '$scope', '$stateParams','$state', 'entity', 'LeaveBalance'];

    function LeaveBalanceDialogController ($timeout, $scope, $stateParams, $state, entity, LeaveBalance) {
        var vm = this;

        vm.leaveBalance = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        	$state.go('leave-balance', null, { reload: 'leave-balance' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.leaveBalance.id !== null) {
                LeaveBalance.update(vm.leaveBalance, onSaveSuccess, onSaveError);
            } else {
                LeaveBalance.save(vm.leaveBalance, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('leaveManagementSystemApp:leaveBalanceUpdate', result);
            $state.go('leave-balance', null, { reload: 'leave-balance' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
