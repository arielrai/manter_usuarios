var routerApp = angular.module('userApp', [ 'ui.router', 'ui.utils.masks' ]);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/users');
	
	$stateProvider

	// HOME STATES AND NESTED VIEWS ========================================
	.state('users', {
		url : '/users',
		templateUrl : 'views/users.html'
	}).state('user', {
		url : '/user?params',
		templateUrl : 'views/user.html'
	});

});

routerApp.controller('appController', function($scope, $rootScope) {
	$rootScope.$on('message', function(events, args){
		$scope.message = args;
	});

	$scope.clearMessages = function(){
		$scope.message = null;
	}
});