(function() {
    'use strict';

    angular
        .module('leaveManagementSystemApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('leave-allocation', {
            parent: 'entity',
            url: '/leave-allocation?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveAllocation.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-allocation/leave-allocations.html',
                    controller: 'LeaveAllocationController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveAllocation');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('leave-allocation-detail', {
            parent: 'leave-allocation',
            url: '/leave-allocation/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.leaveAllocation.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/leave-allocation/leave-allocation-detail.html',
                    controller: 'LeaveAllocationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('leaveAllocation');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LeaveAllocation', function($stateParams, LeaveAllocation) {
                    return LeaveAllocation.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'leave-allocation',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('leave-allocation-detail.edit', {
            parent: 'leave-allocation-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-allocation/leave-allocation-dialog.html',
                    controller: 'LeaveAllocationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveAllocation', function(LeaveAllocation) {
                            return LeaveAllocation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('leave-allocation.new', {
            parent: 'leave-allocation',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-allocation/leave-allocation-dialog.html',
                    controller: 'LeaveAllocationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                teaching: null,
                                canHaveVacation: null,
                                granted: null,
                                noOfLeaves: null,
                                allocationDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('leave-allocation', null, { reload: 'leave-allocation' });
                }, function() {
                    $state.go('leave-allocation');
                });
            }]
        })
        .state('leave-allocation.edit', {
            parent: 'leave-allocation',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-allocation/leave-allocation-dialog.html',
                    controller: 'LeaveAllocationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LeaveAllocation', function(LeaveAllocation) {
                            return LeaveAllocation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-allocation', null, { reload: 'leave-allocation' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('leave-allocation.delete', {
            parent: 'leave-allocation',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/leave-allocation/leave-allocation-delete-dialog.html',
                    controller: 'LeaveAllocationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LeaveAllocation', function(LeaveAllocation) {
                            return LeaveAllocation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('leave-allocation', null, { reload: 'leave-allocation' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
