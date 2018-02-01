(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationHistoryController', LeaveApplicationHistoryController);

    LeaveApplicationHistoryController.$inject = ['LeaveApplicationHistory'];

    function LeaveApplicationHistoryController(LeaveApplicationHistory) {

        var vm = this;

        vm.leaveApplicationHistories = [];

        loadAll();

        function loadAll() {
            LeaveApplicationHistory.query(function(result) {
                vm.leaveApplicationHistories = result;
                vm.searchQuery = null;
            });
        }
    }
})();
