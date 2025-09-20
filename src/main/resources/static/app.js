function consumirApiExterna() {
	event.preventDefault();
	const symbol = document.getElementById('id').value;
	const base_url = 'https://www.alphavantage.co/query?';

	document.getElementById('comboBox').addEventListener('change', function() {
		document.getElementById('comboBox').value = this.functionType;
	});

	const funtion = 'function=TIME_SERIES_INTRADAY&';
	const urlsymbol = 'symbol=' + symbol || 'IBM';
	const interval = '&interval=5min&month=2009-01&';
	const outputsize = 'outputsize=full&';
	const apikey = 'apikey=demo' ;


	fetch(base_url + funtion + urlsymbol + interval + outputsize + apikey)
		.then(response => response.json())
		.then(data => {
			const contenedor = document.getElementById('api-externa');
			contenedor.innerHTML = '<h3>API externa:</h3>';
			const div = document.createElement('div');

			const objetoJS = JSON.parse(data);
			const metaData = objetoJS["Meta Data"];
			const TimeSeries = objetoJS["Time Series (5min)"]; 

			console.log(metaData);
			console.log(TimeSeries);

			div.textContent = metaData;
			div.textContent = TimeSeries;
			contenedor.appendChild(div);
		})
		.catch(error => {
			console.log(" ");
		});
}





