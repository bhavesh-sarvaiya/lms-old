(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveBalanceController', LeaveBalanceController,);

    LeaveBalanceController.$inject = ['LeaveBalance','Principal'];

    function LeaveBalanceController(LeaveBalance,Principal) {

        var vm = this;

       // vm.employees= Employee.query();
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.leaveBalances = [];

        loadAll();

        function  loadAll() {
            LeaveBalance.query(function(result) {
                vm.leaveBalances = result;
                vm.searchQuery = null;
            });
        }
    }
})();
