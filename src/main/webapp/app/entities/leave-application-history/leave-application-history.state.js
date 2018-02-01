(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('leave-application-history', {
            parent: 'entity',
            url: '/leave-application-history',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveApplicationHistory.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-application-history/leave-application-histories.html',
                    controller: 'LeaveApplicationHistoryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveApplicationHistory');
                    $translatePartialLoader.addPart('status');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('leave-application-history-detail', {
            parent: 'leave-application-history',
            url: '/leave-application-history/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveApplicationHistory.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-application-history/leave-application-history-detail.html',
                    controller: 'LeaveApplicationHistoryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveApplicationHistory');
                    $translatePartialLoader.addPart('status');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LeaveApplicationHistory', function($stateParams, LeaveApplicationHistory) {
                    return LeaveApplicationHistory.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-application-history',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('leave-application-history-detail.edit', {
            parent: 'leave-application-history-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application-history/leave-application-history-dialog.html',
                    controller: 'LeaveApplicationHistoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveApplicationHistory', function(LeaveApplicationHistory) {
                            return LeaveApplicationHistory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('leave-application-history.new', {
            parent: 'leave-application-history',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application-history/leave-application-history-dialog.html',
                    controller: 'LeaveApplicationHistoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                status: null,
                                comment: null,
                                actionDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('leave-application-history', null, { reload: 'leave-application-history' });
                }, function() {
                    $state.go('leave-application-history');
                });
            }]
        })
        .state('leave-application-history.edit', {
            parent: 'leave-application-history',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application-history/leave-application-history-dialog.html',
                    controller: 'LeaveApplicationHistoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveApplicationHistory', function(LeaveApplicationHistory) {
                            return LeaveApplicationHistory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-application-history', null, { reload: 'leave-application-history' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('leave-application-history.delete', {
            parent: 'leave-application-history',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-application-history/leave-application-history-delete-dialog.html',
                    controller: 'LeaveApplicationHistoryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LeaveApplicationHistory', function(LeaveApplicationHistory) {
                            return LeaveApplicationHistory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-application-history', null, { reload: 'leave-application-history' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
