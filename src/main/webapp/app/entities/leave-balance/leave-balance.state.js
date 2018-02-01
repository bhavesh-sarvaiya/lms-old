(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('leave-balance', {
            parent: 'entity',
            url: '/leave-balance',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveBalance.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-balance/leave-balances.html',
                    controller: 'LeaveBalanceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveBalance');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('leave-balance-detail', {
            parent: 'leave-balance',
            url: '/leave-balance/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveBalance.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-balance/leave-balance-detail.html',
                    controller: 'LeaveBalanceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveBalance');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LeaveBalance', function($stateParams, LeaveBalance) {
                    return LeaveBalance.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-balance',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('leave-balance-detail.edit', {
            parent: 'leave-balance-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                	templateUrl: 'app/entities/leave-balance/leave-balance-dialog.html',
                    controller: 'LeaveBalanceDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'LeaveBalance', function($stateParams, LeaveBalance) {
                    return LeaveBalance.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-balance',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-balance/leave-balance-dialog.html',
                    controller: 'LeaveBalanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveBalance', function(LeaveBalance) {
                            return LeaveBalance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]*/
        })
        .state('leave-balance.new', {
            parent: 'leave-balance',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-balance/leave-balance-dialog.html',
                    controller: 'LeaveBalanceDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: function () {
                    return {
                        noOfLeave: null,
                        id: null
                    };
                },
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-balance',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-balance/leave-balance-dialog.html',
                    controller: 'LeaveBalanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                noOfLeave: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('leave-balance', null, { reload: 'leave-balance' });
                }, function() {
                    $state.go('leave-balance');
                });
            }]*/
        })
        .state('leave-balance.edit', {
            parent: 'leave-balance',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                	templateUrl: 'app/entities/leave-balance/leave-balance-dialog.html',
                    controller: 'LeaveBalanceDialogController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'LeaveBalance', function($stateParams, LeaveBalance) {
                    return LeaveBalance.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-balance',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
            /*onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-balance/leave-balance-dialog.html',
                    controller: 'LeaveBalanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveBalance', function(LeaveBalance) {
                            return LeaveBalance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-balance', null, { reload: 'leave-balance' });
                }, function() {
                    $state.go('^');
                });
            }]*/
        })
        .state('leave-balance.delete', {
            parent: 'leave-balance',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-balance/leave-balance-delete-dialog.html',
                    controller: 'LeaveBalanceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LeaveBalance', function(LeaveBalance) {
                            return LeaveBalance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-balance', null, { reload: 'leave-balance' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
