(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('employee', {
            parent: 'entity',
            url: '/employee',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'leaveManagementSystemApp.employee.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/employee/employees.html',
                    controller: 'EmployeeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('employee');
                    $translatePartialLoader.addPart('post');
                    $translatePartialLoader.addPart('genderEnum');
                    $translatePartialLoader.addPart('maritalStatus');
                    $translatePartialLoader.addPart('bloodGroup');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('employee-detail', {
            parent: 'employee',
            url: '/employee/{id}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'leaveManagementSystemApp.employee.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/employee/employee-detail.html',
                    controller: 'EmployeeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('employee');
                    $translatePartialLoader.addPart('post');
                    $translatePartialLoader.addPart('genderEnum');
                    $translatePartialLoader.addPart('maritalStatus');
                    $translatePartialLoader.addPart('bloodGroup');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Employee', function($stateParams, Employee) {
                    return Employee.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'employee',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('employee-detail.edit', {
            parent: 'employee-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
                'content@': {
                	templateUrl: 'app/entities/employee/employee-dialog.html',
                    controller: 'EmployeeDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: ['$stateParams','Employee', function($stateParams,Employee) {
                    return Employee.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'employee',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee/employee-dialog.html',
                    controller: 'EmployeeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Employee', function(Employee) {
                            return Employee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]*/
        })
        .state('employee.new', {
            parent: 'employee',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/employee/employee-dialog.html',
                    controller: 'EmployeeDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: function () {
                    return {
                        empEnrollMentNo: null,
                        teachingstaff: null,
                        canHaveVacation: null,
                        post: null,
                        fatherHusbandName: null,
                        gender: null,
                        dob: null,
                        address: null,
                        state: null,
                        city: null,
                        pincode: null,
                        maritalStatus: null,
                        phoneNumber: null,
                        mobileNumber: null,
                        bloodGroup: null,
                        physicalFit: false,
                        joinDate: null,
                        retiredDate: null,
                        qualification: null,
                        payband: null,
                        granted: null,
                        otheNote: null,
                        id: null
                    };
                },
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'employee',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee/employee-dialog.html',
                    controller: 'EmployeeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                empEnrollMentNo: null,
                                teachingstaff: null,
                                canHaveVacation: null,
                                post: null,
                                fatherHusbandName: null,
                                gender: null,
                                dob: null,
                                address: null,
                                state: null,
                                city: null,
                                pincode: null,
                                maritalStatus: null,
                                phoneNumber: null,
                                mobileNumber: null,
                                bloodGroup: null,
                                physicalFit: false,
                                joinDate: null,
                                retiredDate: null,
                                qualification: null,
                                payband: null,
                                granted: null,
                                otheNote: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('employee', null, { reload: 'employee' });
                }, function() {
                    $state.go('employee');
                });
            }]*/
        })
        .state('employee.edit', {
            parent: 'employee',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/employee/employee-dialog.html',
                    controller: 'EmployeeDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: ['$stateParams','Employee', function($stateParams,Employee) {
                    return Employee.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'employee',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee/employee-dialog.html',
                    controller: 'EmployeeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Employee', function(Employee) {
                            return Employee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('employee', null, { reload: 'employee' });
                }, function() {
                    $state.go('^');
                });
            }]*/
        })
        .state('employee.delete', {
            parent: 'employee',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee/employee-delete-dialog.html',
                    controller: 'EmployeeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Employee', function(Employee) {
                            return Employee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('employee', null, { reload: 'employee' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
