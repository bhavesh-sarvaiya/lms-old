(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('leave-type', {
            parent: 'entity',
            url: '/leave-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveType.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-type/leave-types.html',
                    controller: 'LeaveTypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('leave-type-detail', {
            parent: 'leave-type',
            url: '/leave-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveType.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-type/leave-type-detail.html',
                    controller: 'LeaveTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LeaveType', function($stateParams, LeaveType) {
                    return LeaveType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('leave-type-detail.edit', {
            parent: 'leave-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-type/leave-type-dialog.html',
                    controller: 'LeaveTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveType', function(LeaveType) {
                            return LeaveType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('leave-type.new', {
            parent: 'leave-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-type/leave-type-dialog.html',
                    controller: 'LeaveTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                code: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('leave-type', null, { reload: 'leave-type' });
                }, function() {
                    $state.go('leave-type');
                });
            }]
        })
        .state('leave-type.edit', {
            parent: 'leave-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-type/leave-type-dialog.html',
                    controller: 'LeaveTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveType', function(LeaveType) {
                            return LeaveType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-type', null, { reload: 'leave-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('leave-type.delete', {
            parent: 'leave-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-type/leave-type-delete-dialog.html',
                    controller: 'LeaveTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LeaveType', function(LeaveType) {
                            return LeaveType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-type', null, { reload: 'leave-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
