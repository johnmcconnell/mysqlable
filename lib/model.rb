require 'field'

class Model
  attr_accessor :name, :fields

  def self.build(schema, opts)
    params = {}
    params[:name] = schema.name
    params[:fields] = build_fields schema, opts[:schemas]
    new(params)
  end

  def self.build_fields(schema, schemas)
    schema.fields.map { |name, opts| build_field name, opts, schemas }
  end

  def self.build_field(name, opts, schemas)
    name = name
    type = opts['type'] ||
      array_of(opts['references_to'], schemas) ||
      object_of(opts['reference_to'], schemas)
    Field.new(name: name, type: type)
  end

  def self.array_of(model_name, schemas)
    return nil if model_name.nil?
    return "Array[#{model_name}]"
  end

  def self.object_of(model_name, schemas)
    "Model[#{model_name}]"
  end

  def initialize(params)
    @name = params[:name]
    @fields = params[:fields]
  end
end
