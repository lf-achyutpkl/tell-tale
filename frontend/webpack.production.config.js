var webpack = require('webpack');
module.exports = {
    context: __dirname,
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /(node_modules|bower_components)/,
                loaders: ['react-hot', 'babel-loader']
            }
        ]
    },
    output: {
        filename: 'bundle.js'
    }
};
