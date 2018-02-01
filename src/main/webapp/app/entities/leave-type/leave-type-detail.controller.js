(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .controller('LeaveTypeDetailController', LeaveTypeDetailController);

    LeaveTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LeaveType'];

    function LeaveTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, LeaveType) {
        var vm = this;

        vm.leaveType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('leaveManagementSystemApp:leaveTypeUpdate', function(event, result) {
            vm.leaveType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
