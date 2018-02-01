(function() {
    'use strict';

    angular
        .module('lm.Lms')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('lms-allocation', {
            parent: 'app',
            url: '/allocation',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'leaveManagementSystemApp.department.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/lms/allocation/departments.html',
                    controller: 'DepartmentController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('department');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        });
    }

})();
