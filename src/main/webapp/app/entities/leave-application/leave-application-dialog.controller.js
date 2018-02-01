(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationDialogController', LeaveApplicationDialogController);

    LeaveApplicationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$state', 'entity','AlertService', 'LeaveApplication', 'LeaveType', 'LeaveApplicationDataService'];

    function LeaveApplicationDialogController ($timeout, $scope, $stateParams, $state, entity, AlertService, LeaveApplication, LeaveType, LeaveApplicationDataService) {
        var vm = this;

        vm.leaveApplication = entity;
        vm.clear = clear;
        vm.showErrorMessage;
        vm.errorUserExists = null;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.leavetypes = LeaveType.query();
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        	$state.go('leave-application', null, { reload: 'leave-application' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.leaveApplication.id !== null) {
            	
                LeaveApplication.update(vm.leaveApplication, onSaveSuccess, onSaveError);
            } else {
                LeaveApplication.save(vm.leaveApplication, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('leaveManagementSystemApp:leaveApplicationUpdate', result);
            $state.go('leave-application', null, { reload: 'leave-application' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.calculateDays = function(){
        	if(vm.leaveApplication.dateFrom != null && vm.leaveApplication.endDate != null){
        		vm.leaveApplication.noOfDays = LeaveApplicationDataService.getNoOfDays(vm.leaveApplication);
        	
        		//AlertService.error("No of Days are "+vm.leaveApplication.noOfDays);
        		
        		vm.leaveApplication.noOfDays.then(function(value) {
        			  console.log("value: " + value);
        			  //AlertService.error("Values "+value);
        			  vm.days=value;
        			});
        	}
        	
        }
        vm.datePickerOpenStatus.dateFrom = false;
        vm.datePickerOpenStatus.endDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
