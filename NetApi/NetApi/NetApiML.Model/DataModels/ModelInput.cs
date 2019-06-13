//*****************************************************************************************
//*                                                                                       *
//* This is an auto-generated file by Microsoft ML.NET CLI (Command-Line Interface) tool. *
//*                                                                                       *
//*****************************************************************************************

using Microsoft.ML.Data;

namespace NetApiML.Model.DataModels
{
    public class ModelInput
    {
        [ColumnName("BuildingId"), LoadColumn(0)]
        public string BuildingId { get; set; }


        [ColumnName("Product"), LoadColumn(1)]
        public string Product { get; set; }


        [ColumnName("Floor"), LoadColumn(2)]
        public string Floor { get; set; }


        [ColumnName("ConsumedQuantity"), LoadColumn(3)]
        public float ConsumedQuantity { get; set; }


        [ColumnName("SupplyDate"), LoadColumn(4)]
        public string SupplyDate { get; set; }


    }
}
