function myController($scope, $http) {


	$scope.getUserDataFromServer = function() {           
		$http.get('http://localhost:8080/MoviePoll/activeUsers').success(function(data) {

			$scope.users = data;				
		})
	};
};             	

angular.module("myApp", []).controller("myController", ["$scope", "$http", myController]);