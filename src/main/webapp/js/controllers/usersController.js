var myApp = angular.module('userApp');

myApp.controller('usersController', ['$scope', '$rootScope', '$http' ,'$state', function($scope, $rootScope, $http, $state) {
	$scope.listUsuarios = function(){
		$http.get("/manter_usuario/rest/users").then(function(response){
			$scope.usuarios = response.data;
		});
	}
	$scope.listUsuarios();
	
	$http.get("/manter_usuario/rest/users/comboOptions").then(function(response){
		$scope.comboOptions = response.data;
	});

	$scope.excluir = function(bean){
		var confirmation = confirm("Deseja realmente excluir o usuario " + bean.nome + "?");
		if (confirmation){
			$http.delete("/manter_usuario/rest/users/" + bean.cpf).then(function(response){
				$rootScope.$broadcast('message', {type:'success', text: response.data})
				$scope.listUsuarios();
			});
		} else {
			//nada faz
		}
	}
	
	$scope.doSearch = function(search){
		$http.post("/manter_usuario/rest/users/search", search).then(function(response){
			if (Object.keys(search).length == 0) {
				$scope.listUsuarios()
			}else{
				$scope.usuarios = response.data;
			}
		});
	}

	$scope.getHref = function(bean){
		return $state.href("user", {params: angular.toJson(bean)});
	};

}]);