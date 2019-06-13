//*****************************************************************************************
//*                                                                                       *
//* This is an auto-generated file by Microsoft ML.NET CLI (Command-Line Interface) tool. *
//*                                                                                       *
//*****************************************************************************************

using System;
using System.IO;
using System.Linq;
using Microsoft.ML;
using NetApiML.Model.DataModels;


namespace NetApiML.ConsoleApp
{
    class Program
    {
        //Machine Learning model to load and use for predictions
        private const string MODEL_FILEPATH = @"../../../NetApiML.Model/MLModel.zip";

        //Dataset to use for predictions 
        private const string DATA_FILEPATH = @"E:\smart-buildings\NetApi\NetApi\data\consumption.csv";
        private const string TEST_FILEPATH = @"E:\smart-buildings\NetApi\NetApi\data\prediction.csv";

        public static String MLAlgorithm()
        {
            MLContext mlContext = new MLContext();

            // Training code used by ML.NET CLI and AutoML to generate the model
            //ModelBuilder.CreateModel();

            ITransformer mlModel = mlContext.Model.Load(GetAbsolutePath(MODEL_FILEPATH), out DataViewSchema inputSchema);
            var predEngine = mlContext.Model.CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);

            String message = makePredictions(mlContext, TEST_FILEPATH, predEngine);
            return message;
        }

        // Method to load single row of data to try a single prediction
        // You can change this code and create your own sample data here (Hardcoded or from any source)
        private static ModelInput CreateSingleDataSample(MLContext mlContext, string dataFilePath)
        {
            // Read dataset to get a single row for trying a prediction          
            IDataView dataView = mlContext.Data.LoadFromTextFile<ModelInput>(
                                            path: dataFilePath,
                                            hasHeader: true,
                                            separatorChar: ',',
                                            allowQuoting: true,
                                            allowSparse: false);

            // Here (ModelInput object) you could provide new test data, hardcoded or from the end-user application, instead of the row from the file.
            ModelInput sampleForPrediction = mlContext.Data.CreateEnumerable<ModelInput>(dataView, false)
                                                                        .First();
            return sampleForPrediction;
        }

        private static String makePredictions(MLContext mlContext, string testFilePath, PredictionEngine<ModelInput, ModelOutput> predEngine)
        {
            // Read dataset to get a single row for trying a prediction          
            IDataView dataView = mlContext.Data.LoadFromTextFile<ModelInput>(
                                            path: testFilePath,
                                            hasHeader: true,
                                            separatorChar: ',',
                                            allowQuoting: true,
                                            allowSparse: false);
            String message = "";

            foreach (ModelInput sampleForPrediction in mlContext.Data.CreateEnumerable<ModelInput>(dataView, false))
            {
                ModelOutput predictionResult = predEngine.Predict(sampleForPrediction);
                message += predictionResult.Prediction + "@";
            }

            return message;
        }

        public static string GetAbsolutePath(string relativePath)
        {
            FileInfo _dataRoot = new FileInfo(typeof(Program).Assembly.Location);
            string assemblyFolderPath = _dataRoot.Directory.FullName;

            string fullPath = Path.Combine(assemblyFolderPath, relativePath);

            return fullPath;
        }
    }
}
