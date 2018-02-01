(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveTypeController', LeaveTypeController);

    LeaveTypeController.$inject = ['LeaveType'];

    function LeaveTypeController(LeaveType) {

        var vm = this;

        vm.leaveTypes = [];

        loadAll();

        function loadAll() {
            LeaveType.query(function(result) {
                vm.leaveTypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
