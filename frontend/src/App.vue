// 📁 File: frontend/src/App.vue
<template>
  <div class="p-6 max-w-4xl mx-auto">
    <h1 class="text-2xl font-bold mb-4">👨‍💼 Employee Training Manager</h1>

    <div class="grid grid-cols-2 gap-8">
      <!-- Employee List -->
      <div>
        <h2 class="text-xl font-semibold mb-2">Employees</h2>
        <ul class="space-y-2">
          <li v-for="employee in employees" :key="employee.id" class="border p-2 rounded">
            {{ employee.name }} <br />📧 {{ employee.email }}
          </li>
        </ul>
      </div>

      <!-- Training List with Assign Button -->
      <div>
        <h2 class="text-xl font-semibold mb-2">Trainings</h2>
        <ul class="space-y-4">
          <li v-for="training in trainings" :key="training.id" class="border p-2 rounded">
            <div>
              <strong>{{ training.title }}</strong> <br />📝 {{ training.description }}
            </div>
            <div class="mt-2">
              <label class="text-sm">Assign to:</label>
              <select v-model="selectedEmployeeId[training.id]" class="border px-2 py-1 rounded">
                <option v-for="employee in employees" :value="employee.id" :key="employee.id">
                  {{ employee.name }}
                </option>
              </select>
              <button @click="assignTraining(training.id)" class="ml-2 bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700">
                Assign
              </button>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!-- GPT Mock Response -->
    <div v-if="gptResponse" class="mt-6 p-4 border rounded bg-gray-50">
      <h3 class="font-semibold text-lg mb-2">🤖 GPT Recommendation:</h3>
      <pre class="whitespace-pre-wrap">{{ gptResponse }}</pre>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const employees = ref([])
const trainings = ref([])
const gptResponse = ref('')
const selectedEmployeeId = ref({})

const fetchEmployees = async () => {
  const res = await axios.get('/api/employees')
  employees.value = res.data
}

const fetchTrainings = async () => {
  const res = await axios.get('/api/trainings')
  trainings.value = res.data.filter(t => t.id === 1 || t.id === 2)
}

const assignTraining = async (trainingId) => {
  const employeeId = selectedEmployeeId.value[trainingId]
  if (!employeeId) return alert('Please select an employee.')

  await axios.post(`/api/employees/${employeeId}/assign-training/${trainingId}`)

  // Simulate GPT response (since API is mocked for now)
  gptResponse.value = `1. Advanced Java\n2. Spring Boot Fundamentals\n3. Microservices Architecture`
}

onMounted(() => {
  fetchEmployees()
  fetchTrainings()
})
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
}
</style>
