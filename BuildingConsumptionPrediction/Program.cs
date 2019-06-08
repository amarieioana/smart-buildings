using System;
using System.IO;
using Microsoft.ML;

namespace BuildingConsumptionPrediction
{
    class Program
    {
        static readonly string _DataPath = Path.Combine(Environment.CurrentDirectory, "Data", "consumption.csv");
        static readonly string _modelPath = Path.Combine(Environment.CurrentDirectory, "Data", "Model.zip");

        static void Main(string[] args)
        {
            MLContext mlContext = new MLContext(seed: 0);

            IDataView data = mlContext.Data.LoadFromTextFile<BuildingConsumption>(_DataPath, hasHeader: true, separatorChar: ',');
            DataOperationsCatalog.TrainTestData dataSplit = mlContext.Data.TrainTestSplit(data, testFraction: 0.3);
            IDataView trainData = dataSplit.TrainSet;
            IDataView testData = dataSplit.TestSet;

            var model = Train(mlContext, trainData);
            Evaluate(mlContext, model, testData);
            TestSinglePrediction(mlContext, model);
        }

        public static ITransformer Train(MLContext mlContext, IDataView trainData)
        {
            /*  var pipelineForConsumedQuantity = mlContext.Transforms.CopyColumns(outputColumnName: "Label", inputColumnName: "ConsumedQuantity")
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "BuildingIdEncoded", inputColumnName: "BuildingId"))
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "ProductEncoded", inputColumnName: "Product"))
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "FloorEncoded", inputColumnName: "Floor"))
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "SupplyDateEncoded", inputColumnName: "SupplyDate"))
                                      .Append(mlContext.Transforms.Concatenate("Features", "BuildingIdEncoded", "ProductEncoded", "FloorEncoded", "ConsumedQuantity", "SupplyDateEncoded"))
                                      .Append(mlContext.Regression.Trainers.FastTree())
                                      .Append(mlContext.Transforms.CopyColumns(outputColumnName: "consumedQuantity", inputColumnName: "Score"));

              var pipelineForSupplyDate = mlContext.Transforms.CopyColumns(outputColumnName: "Label", inputColumnName: "SupplyDate")
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "BuildingIdEncoded", inputColumnName: "BuildingId"))
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "ProductEncoded", inputColumnName: "Product"))
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "FloorEncoded", inputColumnName: "Floor"))
                                      .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "SupplyDateEncoded", inputColumnName: "SupplyDate"))
                                      .Append(mlContext.Transforms.Concatenate("Features", "BuildingIdEncoded", "ProductEncoded", "FloorEncoded", "ConsumedQuantity", "SupplyDateEncoded"))
                                      .Append(mlContext.Regression.Trainers.FastTree())
                                      .Append(mlContext.Transforms.CopyColumns(outputColumnName: "supplyDate", inputColumnName: "Score")); 

            var model = pipelineForConsumedQuantity.Append(pipelineForSupplyDate).Fit(trainData);*/

            var pipeline = mlContext.Transforms.CopyColumns(outputColumnName: "Label", inputColumnName: "SupplyDate")
                                    .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "BuildingIdEncoded", inputColumnName: "BuildingId"))
                                    .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "ProductEncoded", inputColumnName: "Product"))
                                    .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "FloorEncoded", inputColumnName: "Floor"))
                                    .Append(mlContext.Transforms.Categorical.OneHotEncoding(outputColumnName: "SupplyDateEncoded", inputColumnName: "SupplyDate"))
                                    .Append(mlContext.Transforms.Concatenate("Features", "BuildingIdEncoded", "ProductEncoded", "FloorEncoded", "ConsumedQuantity", "SupplyDateEncoded"))
                                    .Append(mlContext.Regression.Trainers.FastTree());

            var model = pipeline.Fit(trainData);

            return model;
        }

        private static void Evaluate(MLContext mlContext, ITransformer model, IDataView testData)
        {
       
            var predictions = model.Transform(testData);
            var metrics = mlContext.Regression.Evaluate(predictions, "Label", "Score");
            Console.WriteLine();
            Console.WriteLine($"*************************************************");
            Console.WriteLine($"*       Model quality metrics evaluation         ");
            Console.WriteLine($"*------------------------------------------------");

            Console.WriteLine($"*       RSquared Score:      {metrics.RSquared:0.##}");
            Console.WriteLine($"*       Root Mean Squared Error:      {metrics.RootMeanSquaredError:#.##}");
        }

        private static void TestSinglePrediction(MLContext mlContext, ITransformer model)
        {
            var predictionFunction = mlContext.Model.CreatePredictionEngine<BuildingConsumption, BuildingConsumptionPrediction>(model);

        }
    }
}
