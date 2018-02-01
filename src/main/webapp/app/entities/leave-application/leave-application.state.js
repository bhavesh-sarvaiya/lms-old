(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('leave-application', {
            parent: 'entity',
            url: '/leave-application',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveApplication.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-application/leave-applications.html',
                    controller: 'LeaveApplicationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveApplication');
                    $translatePartialLoader.addPart('status');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('leave-application-detail', {
            parent: 'leave-application',
            url: '/leave-application/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveApplication.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-application/leave-application-detail.html',
                    controller: 'LeaveApplicationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveApplication');
                    $translatePartialLoader.addPart('status');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LeaveApplication', function($stateParams, LeaveApplication) {
                    return LeaveApplication.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-application',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('leave-application-detail.edit', {
            parent: 'leave-application-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                	templateUrl: 'app/entities/leave-application/leave-application-dialog.html',
                    controller: 'LeaveApplicationDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'LeaveApplication', function($stateParams, LeaveApplication) {
                    return LeaveApplication.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-application',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application/leave-application-dialog.html',
                    controller: 'LeaveApplicationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveApplication', function(LeaveApplication) {
                            return LeaveApplication.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]*/
        })
        .state('leave-application.new', {
            parent: 'leave-application',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-application/leave-application-dialog.html',
                    controller: 'LeaveApplicationDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: function () {
                    return {
                        reason: null,
                        status: null,
                        dateFrom: null,
                        endDate: null,
                        id: null
                    };
                },
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-application',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application/leave-application-dialog.html',
                    controller: 'LeaveApplicationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                reason: null,
                                status: null,
                                dateFrom: null,
                                endDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('leave-application', null, { reload: 'leave-application' });
                }, function() {
                    $state.go('leave-application');
                });
            }]*/
        })
        .state('leave-application.edit', {
            parent: 'leave-application',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                	templateUrl: 'app/entities/leave-application/leave-application-dialog.html',
                    controller: 'LeaveApplicationDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'LeaveApplication', function($stateParams, LeaveApplication) {
                    return LeaveApplication.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-application',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application/leave-application-dialog.html',
                    controller: 'LeaveApplicationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveApplication', function(LeaveApplication) {
                            return LeaveApplication.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-application', null, { reload: 'leave-application' });
                }, function() {
                    $state.go('^');
                });
            }]*/
        })
        .state('leave-application.delete', {
            parent: 'leave-application',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application/leave-application-delete-dialog.html',
                    controller: 'LeaveApplicationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LeaveApplication', function(LeaveApplication) {
                            return LeaveApplication.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-application', null, { reload: 'leave-application' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
