export interface AssignedTask {
  id: number;
  title: string;
  description: string;
  completed: boolean;
  modulePriority: number;
  absolutePriority: number;
  trainingModuleId: number;
  userId: number;
  taskId: number;

}
