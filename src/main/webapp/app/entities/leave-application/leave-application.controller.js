(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationController', LeaveApplicationController);

    LeaveApplicationController.$inject = ['LeaveApplication'];

    function LeaveApplicationController(LeaveApplication) {

        var vm = this;

        vm.leaveApplications = [];

        loadAll();

        function loadAll() {
            LeaveApplication.query(function(result) {
                vm.leaveApplications = result;
                vm.searchQuery = null;
            });
        }
    }
})();
