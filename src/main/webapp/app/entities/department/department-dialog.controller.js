(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('DepartmentDialogController', DepartmentDialogController);

    DepartmentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$state', 'entity', 'DepartmentService'];

    function DepartmentDialogController ($timeout, $scope, $stateParams, $state, entity, DepartmentService) {
        var vm = this;

        vm.department = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        	$state.go('department', null, { reload: 'department' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.department.id !== null) {
                DepartmentService.update(vm.department, onSaveSuccess, onSaveError);
            } else {
                DepartmentService.save(vm.department, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('leaveManagementSystemApp:departmentUpdate', result);
            $state.go('department', null, { reload: 'department' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
