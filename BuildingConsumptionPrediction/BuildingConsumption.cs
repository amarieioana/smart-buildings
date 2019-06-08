using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.ML.Data;

namespace BuildingConsumptionPrediction
{
    public class BuildingConsumption
    {
        [LoadColumn(0)]
        public string BuildingId;

        [LoadColumn(1)]
        public string Product;

        [LoadColumn(2)]
        public string Floor;

        [LoadColumn(3)]
        public float ConsumedQuantity;

        [LoadColumn(4)]
        public DateTime SupplyDate;
    }

    public class BuildingConsumptionPrediction
    {
        /*[ColumnName("consumedQuantity")]
        public float ConsumedQuantity;

        [ColumnName("supplyDate")]
        public DateTime SupplyDate; */

        [ColumnName("Score")]
        public DateTime SupplyDate;
    }
}
