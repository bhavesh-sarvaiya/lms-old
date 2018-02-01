(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveApplicationHistoryDetailController', LeaveApplicationHistoryDetailController);

    LeaveApplicationHistoryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LeaveApplicationHistory'];

    function LeaveApplicationHistoryDetailController($scope, $rootScope, $stateParams, previousState, entity, LeaveApplicationHistory) {
        var vm = this;

        vm.leaveApplicationHistory = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('leaveManagementSystemApp:leaveApplicationHistoryUpdate', function(event, result) {
            vm.leaveApplicationHistory = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
