'use strict';

describe('Controller Tests', function() {

    describe('LeaveApplication Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockLeaveApplication, MockLeaveType;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockLeaveApplication = jasmine.createSpy('MockLeaveApplication');
            MockLeaveType = jasmine.createSpy('MockLeaveType');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'LeaveApplication': MockLeaveApplication,
                'LeaveType': MockLeaveType
            };
            createController = function() {
                $injector.get('$controller')("LeaveApplicationDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'leaveManagementSystemApp:leaveApplicationUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
