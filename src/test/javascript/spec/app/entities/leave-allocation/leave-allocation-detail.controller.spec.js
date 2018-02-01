'use strict';

describe('Controller Tests', function() {

    describe('LeaveAllocation Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockLeaveAllocation, MockLeaveType;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockLeaveAllocation = jasmine.createSpy('MockLeaveAllocation');
            MockLeaveType = jasmine.createSpy('MockLeaveType');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'LeaveAllocation': MockLeaveAllocation,
                'LeaveType': MockLeaveType
            };
            createController = function() {
                $injector.get('$controller')("LeaveAllocationDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'leaveManagementSystemApp:leaveAllocationUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
