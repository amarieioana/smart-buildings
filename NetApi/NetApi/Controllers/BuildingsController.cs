using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace NetApi.Controllers
{
    
    public class BuildingsController : Controller
    {

        [Route("algorithm")]
        [HttpGet]
        public string startAlgoritm()
        {
            return NetApiML.ConsoleApp.Program.MLAlgorithm();
        }

        [Route("singlePrediction")]
        [HttpGet]
        public string getSinglePrediction()
        {
            return NetApiML.ConsoleApp.Program.getSinglePrediction();
        }

        /*// GET api/values
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        [HttpPost]
        public void Post([FromBody]string value)
        {
        }

        [HttpPut("{id}")]
        public void Put(int id, [FromBody]string value)
        {
        }

        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }*/
    }
}
