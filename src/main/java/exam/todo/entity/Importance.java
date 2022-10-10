package exam.todo.entity;

public enum Importance {
    URGENT(3),
    IMPORTANT(2),
    NON_URGENT(1);

    private Integer value;

   private Importance(Integer value) {
       this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
