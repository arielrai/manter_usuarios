var myApp = angular.module('userApp');

myApp.controller('userController', ['$scope', '$rootScope', '$http', '$stateParams', '$state', function($scope, $rootScope, $http, $stateParams, $state) {

	$http.get('/manter_usuario/rest/users/comboOptions').then(function(response){
		$scope.comboOptions = response.data;
	});
	
	$scope.doSave = function(bean){
		$http.post('/manter_usuario/rest/users', bean).then(function(response){
			if(response.status == 200){
				$scope.bean = {};
				$rootScope.$broadcast('message', {type:'success', text: response.data})
			}
		}, function(response){
			$rootScope.$broadcast('message', {type:'error', text: response.data})
		});
	}

	$scope.goBackUsers = function(){
		$state.go('users');
	}
}]);